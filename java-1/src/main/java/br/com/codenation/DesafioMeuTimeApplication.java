package br.com.codenation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.exceptions.DesafioMeuTimeException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private List<Time> times;
	private List<Jogador> jogadores;

	public DesafioMeuTimeApplication(List<Time> times, List<Jogador> jogadores) {
		this.times = new ArrayList<Time>();
		this.jogadores = new ArrayList<Jogador>();
	}

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if(times.stream().anyMatch(x -> x.getId().equals(id)))
			throw new DesafioMeuTimeException("Id em uso por outro Time"); //UnsupportedOperationException();

		Time novoTime = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
		times.add(novoTime);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if(jogadores.stream().anyMatch(x -> x.getId().equals(id)))
			throw new DesafioMeuTimeException("Id em uso por outro Jogador");

		Time time = recuperarTimePorId(idTime);

		Jogador novoJogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
		jogadores.add(novoJogador);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Jogador jogador = recuperarJogadorPorId(idJogador);
		Time time = recuperarTimePorId(jogador.getIdTime());

		time.setCapitao(jogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = recuperarTimePorId(idTime);

		if(time.getCapitao() == null)
			throw new DesafioMeuTimeException("buscarCapitaoDoTime");

		return time.getCapitao().getId();
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		Jogador jogador = recuperarJogadorPorId(idJogador);
		return jogador.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Time time = recuperarTimePorId(idTime);
		return time.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Time time = recuperarTimePorId(idTime);

		return jogadores
				.stream()
				.filter(x -> x.getIdTime().equals(time.getId()))
				.map(Jogador::getId)
				.sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Time time = recuperarTimePorId(idTime);

		return jogadores
				.stream()
				.filter(x -> x.getIdTime().equals(time.getId()))
				.sorted(Comparator.comparing(Jogador::getId))
				.max(Comparator.comparingInt(Jogador::getNivelHabilidade))
				.map(Jogador::getId)
				.orElseThrow(() -> new DesafioMeuTimeException("buscarMelhorJogadorDoTime"));
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Time time = recuperarTimePorId(idTime);
		return jogadores
				.stream()
				.filter(x -> x.getIdTime().equals(time.getId()))
				.min(Comparator.comparing(Jogador::getDataNascimento).thenComparing(Jogador::getId))
				.map(Jogador::getId)
				.orElseThrow(() -> new DesafioMeuTimeException("buscarJogadorMaisVelho"));
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times.stream()
				.sorted(Comparator.comparing(Time::getId))
				.map(Time::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		Time time = recuperarTimePorId(idTime);

		return jogadores
				.stream()
				.filter(x -> x.getIdTime().equals(time.getId()))
				.sorted(Comparator.comparing(Jogador::getId))
				.max(Comparator.comparing(Jogador::getSalario))
				.map(Jogador::getId)
				.orElseThrow(() -> new DesafioMeuTimeException("buscarJogadorMaiorSalario"));
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Jogador jogador = recuperarJogadorPorId(idJogador);
		return jogador.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return jogadores
				.stream()
				.sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed()
						.thenComparing(Jogador::getId))
				.limit(top)
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		String corDaCamisa = null;

		Time _TimeDaCasa = recuperarTimePorId(timeDaCasa);
		Time _TimeDeFora = recuperarTimePorId(timeDeFora);

		if(_TimeDaCasa.getCorUniformePrincipal().equalsIgnoreCase(_TimeDeFora.getCorUniformePrincipal())) {
			corDaCamisa = _TimeDeFora.getCorUniformeSecundario();
		} else {
			corDaCamisa = _TimeDeFora.getCorUniformePrincipal();
		}

		return corDaCamisa;
	}

	private Time recuperarTimePorId(Long id) {
		return times
				.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst()
				.orElseThrow( () -> new DesafioMeuTimeException("Time nao existe!") );
	}

	private Jogador recuperarJogadorPorId(Long id) {
		return jogadores
				.stream()
				.filter(x -> x.getId().equals(id))
				.findFirst()
				.orElseThrow( () -> new DesafioMeuTimeException("Jogador nao existe!") );
	}

}
