export interface Result {
  result:     ProductDetails[];
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

export interface ProductDetails {
  id:                 number;
  nombre:             string;
  inventario:         string;
  categoria:          string;
  cantidadDisponible: number;
  nivelMinimoStock:   number;
}
