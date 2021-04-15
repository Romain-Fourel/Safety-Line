import { Component, OnInit } from '@angular/core';
import { MovieService } from './services/movie.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  static isConnected:boolean=false;

  constructor(){
    
  }

  ngOnInit(){
  }

  getIsConnected(){
    return MovieService.idUser!==undefined;
  }

  onDisconnect(){
    if(confirm("Do you really want to disconnect ?")){
      MovieService.idUser = undefined;
    }
  }

}

