package com.my.diplom.repository;

import com.my.diplom.entities.Item;
import com.my.diplom.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    //Page<Question> findAll(Pageable pageable);

    //List<Question> findBySenderEmail(String sender, Pageable pageable);

    //Page<Question> findByResponsible(String responsible, Pageable pageable);
}
