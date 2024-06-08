export interface Result {
  result:     Inventary[];
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

export interface Inventary {
  id:          number;
  nombre:      string;
  descripcion: string;
}
