package com.facturacion.stockmaster.repository;

import com.facturacion.stockmaster.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(value = """
            select i.id as inventary_id,
            i.nombre,
            c.id as categoriaId,
            CONCAT(CAST(c.id AS TEXT), '-', c.nombre) as categoria,
            count(p.id) as cantidad_productos
            from inventario i
            inner join productos p on i.id = p.inventario_id
            inner join categoria c on p.categoria_id = c.id
            group by i.id, i.nombre, c.id, c.nombre
            """, countQuery = """
                select count(*)
                from (
                    select i.id as inventary_id,
                    i.nombre,
                    c.id as categoriaId,
                    CONCAT(CAST(c.id AS TEXT), '-', c.nombre) as categoria,
                    count(p.id) as cantidad_productos
                    from inventario i
                    inner join productos p on i.id = p.inventario_id
                    inner join categoria c on p.categoria_id = c.id
                    group by i.id, i.nombre, c.id, c.nombre
                ) as subquery;
            """, nativeQuery = true)
    Page<Object[]> getInventaryProducts(Pageable pageable);

    @Query(value = """
        select distinct p.id, p.nombre, p.descripcion,CONCAT(CAST(i.id AS TEXT), '-', i.nombre) as inventario,
               CONCAT(CAST(c.id AS TEXT), '-', c.nombre) as categoria, p.cantidad_disponible, p.nivel_minimo_stock,p.precio_unitario  from productos p
        inner join inventario i on p.inventario_id = i.id
        inner join categoria c on p.categoria_id = c.id
        where p.inventario_id = :inventaryId
    """, countQuery = """
    select count(*)
    from (
        select distinct p.id, p.nombre, p.descripcion,CONCAT(CAST(i.id AS TEXT), '-', i.nombre) as inventario,
           CONCAT(CAST(c.id AS TEXT), '-', c.nombre) as categoria, p.cantidad_disponible, p.nivel_minimo_stock,p.precio_unitario  from productos p
    inner join inventario i on p.inventario_id = i.id
    inner join categoria c on p.categoria_id = c.id
    where p.inventario_id = :inventaryId
         ) as subquery""", nativeQuery = true)
    Page<Object[]> getProductsInventaryById(@Param("inventaryId") Long inventaryId, Pageable pageable);
}
