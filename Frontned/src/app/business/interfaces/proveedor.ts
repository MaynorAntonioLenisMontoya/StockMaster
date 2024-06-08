export interface Result {
  result:     Proveedor[];
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

export interface Proveedor {
  id:          number;
  nombre:      string;
  email:       string;
  telefono:    string;
}
