import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class ApiService {

  constructor(private httpClient: HttpClient) {
  }

  get(path: String) {
		const url = 'http://localhost:8080';
    return this.httpClient.get(`${url}/${path}`);
  }

}
