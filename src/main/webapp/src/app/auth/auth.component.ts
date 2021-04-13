import { Component, Input, OnInit } from '@angular/core';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent implements OnInit {

  @Input() userName:string;
  @Input() password:string;

  constructor(private movieService:MovieService) { }

  ngOnInit() {
  }

  onConnection(){
    let data = [this.userName,this.password];
    this.movieService.getMOviesFromUser(data);
    
  }

}
