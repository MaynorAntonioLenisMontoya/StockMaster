export interface Result {
  result:     TipoTransicion[];
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

export interface TipoTransicion {
  id: number;
  nombre: string;
}
