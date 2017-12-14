import { Component, Input } from '@angular/core';
import { Http } from '@angular/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'Angular Password';

    @Input() senha: string;
    http: Http;
    password = {};

    constructor(http: Http){
      this.http = http;
    };

    verificarSenha(){
      this.http.get("api/passwordvalidation?senha=" + this.senha)
          .map(res => res.json())
          .subscribe(pass =>this.password = pass), error => console.log(" ", error);
    }
}