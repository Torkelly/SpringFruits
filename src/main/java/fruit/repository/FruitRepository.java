  
package fruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fruit.beans.Fruit;

public interface FruitRepository extends JpaRepository<Fruit, Long> { }