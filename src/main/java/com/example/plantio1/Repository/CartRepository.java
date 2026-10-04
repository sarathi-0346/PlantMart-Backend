package com.example.plantio1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plantio1.Entity.Card;

@Repository
public interface CartRepository extends JpaRepository<Card, Long>{
	
	 Optional<Card> findByUserIdAndProductId(
	            Long userId,
	            Long productId
	    );

	    List<Card> findByUserId(Long userId);
	    

}
