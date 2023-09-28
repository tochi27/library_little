package com.purplecloud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibRepository extends JpaRepository<Library, Integer> {

}
