import { Component, OnInit, Input,TemplateRef } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.scss']
})
export class MovieComponent implements OnInit {

  @Input() movieId : number;

  modalRef: BsModalRef;

  constructor(private movieService:MovieService,
              private modalService:BsModalService) { 
    this.movieId = MovieService.counter;
  }

  ngOnInit() {
  }

  /**
   * Getters to make the code cleaner:
   */

  getMovie(){
    return this.movieService.movies[this.movieId];
  }

  getMovieInfos(){
    return this.getMovie().info;
  }  

  changeFavorite(){
    this.getMovie().isFavorite = !this.getMovie().isFavorite;
  }

  onDelete(id:number){
    this.movieService.deleteMovie(id);
  }

  showInfos(template:TemplateRef<any>){
    this.modalRef = this.modalService.show(template);
  }

}
