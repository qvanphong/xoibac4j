package tech.qvanphong.xoibac4j;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import tech.qvanphong.xoibac4j.model.Bettor;

import java.util.List;

public interface BetRepository extends ReactiveCrudRepository<Bettor, String> {

    @Override
    <S extends Bettor> Mono<S> save(S entity);

    Mono<Bettor> findBettorById(String id);

    @Override
    Mono<Void> deleteAll();

    @Override
    Mono<Long> count();
}
