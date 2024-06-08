

export interface LoginResponse {
  result:     Result;
  message:    string;
  resultCode: number;
  paginator:  null;
}

export interface Result {
  token: string;
}

