package inhatc.spring.shop.repository;

import inhatc.spring.shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CartRepository extends JpaRepository<Cart, Long>, QuerydslPredicateExecutor<Cart> {


}
