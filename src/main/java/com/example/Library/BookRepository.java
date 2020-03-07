/**
 * Created by Bogoslovskiy Denis 2020
 */
package com.example.Library;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {


}
