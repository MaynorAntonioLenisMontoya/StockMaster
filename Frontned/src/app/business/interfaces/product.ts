import { Category } from "./category";
import { Inventary } from "./inventary";

export interface Result {
  result:     InventaryProduct[];
  message:    string;
  resultCode: number;
  paginator:  Paginator;
}

export interface Result_Product {
  result:     ProductsInventary[];
  message:    string;
  resultCode: number;
  paginator:  Paginator;
}

export interface Paginator {
  pageNumber:         number;
  pageSize:           number;
  totalItems:         number;
  totalPages:         number;
  hasNextPage:        boolean;
  hasPreviousPage:    boolean;
  nextPageNumber:     null;
  previousPageNumber: null;
}

export interface InventaryProduct {
  inventaryId:       number;
  nombre:            string;
  categoriaId:       number;
  categoria:         string;
  cantidadProductos: number;
}

export interface ProductsInventary {
  id:                number;
  nombre:            string;
  inventario:         string;
  categoria:         string;
  cantidadDisponible: number;
  nivelMinimoStock: number;
}

export interface Producto {
  id: number;
  nombre: string;
  descripcion: string;
  categoriaId: number;
  precioUnitario: number;
  cantidadDisponible: number;
  nivelMinimoStock: number;
  inventarioId: number;
}

export interface ProductoForm {
  id: number;
  nombre: string;
  descripcion: string;
  categoriaId?: Category;
  precioUnitario: number;
  cantidadDisponible: number;
  nivelMinimoStock: number;
  inventarioId?: Inventary;
}
