import {Injectable} from '@angular/core';
import 'rxjs/add/operator/map';
import {UserDto} from './user.dto';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UserLoginService {
  constructor(private http: HttpClient) {
  }

  loginUser(email: string, password: string ) {
       return this.http.post<any>('http://localhost:9071/api/v1/login', {email: email, password: password})
          // this is just the HTTP call,
          // we still need to handle the reception of the token
          .pipe(map(user => {
              // login successful if there's a jwt token in the response
            //   console.log(user.email);
              
              if (user) {
                  // store user details and jwt token in local storage to keep user logged in between page refreshes
                  localStorage.setItem('currentUserEmail',email);
                  localStorage.setItem('currentUser', JSON.stringify(user));
              }
              return user;
          }));
  }
  logout() {
      // remove user from local storage to log user out
      localStorage.removeItem('currentUserEmail');
      localStorage.removeItem('currentUser');
  } 
}
