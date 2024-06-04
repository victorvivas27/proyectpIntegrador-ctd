package com.musichouse.api.music.repository;

import com.musichouse.api.music.entity.Category;
import com.musichouse.api.music.entity.Instrument;
import com.musichouse.api.music.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    /**
     * Encuentra una lista de instrumentos que pertenecen a una categoría específica.
     *
     * @param category la categoría de los instrumentos que se desea encontrar.
     * @return una lista de instrumentos que pertenecen a la categoría especificada.
     *
     * Ejemplo en la base de datos:
     * Si tienes una categoría con el nombre "Cuerdas", la consulta JPQL sería:
     * SELECT i FROM Instrument i WHERE i.category = :category
     */
    List<Instrument> findByCategory(Category category);

    /**
     * Encuentra una lista de instrumentos que pertenecen a un tema específico.
     *
     * @param theme el tema de los instrumentos que se desea encontrar.
     * @return una lista de instrumentos que pertenecen al tema especificado.
     *
     * Ejemplo en la base de datos:
     * Si tienes un tema con el nombre "Clásico", la consulta JPQL sería:
     * SELECT i FROM Instrument i WHERE i.theme = :theme
     */
    List<Instrument> findByTheme(Theme theme);

    /**
     * Encuentra una lista de instrumentos cuyo nombre contenga una cadena específica, ignorando mayúsculas y minúsculas.
     *
     * @param name la cadena que debe estar contenida en el nombre de los instrumentos.
     * @return una lista de instrumentos cuyos nombres contienen la cadena especificada, ignorando mayúsculas y minúsculas.
     *
     * Ejemplo en la base de datos:
     * Si buscas instrumentos cuyo nombre contenga "guitarra", la consulta JPQL sería:
     * SELECT i FROM Instrument i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :name, '%'))
     */
    List<Instrument> findByNameContainingIgnoreCase(String name);
}
