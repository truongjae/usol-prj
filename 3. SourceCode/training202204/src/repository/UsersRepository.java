package repository;

import entity.Users;
import orm.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long>{
}
