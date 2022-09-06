package repository;

import entity.Events;
import orm.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, Long>{
}
