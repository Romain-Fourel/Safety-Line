import { HttpClient} from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class MovieService{
    
    //all deleted movies are kept in the array until the end of the program...
    //it could be a problem... or not !
    movies=[];

    static isConnected:boolean=false;

    static searchedMovies:any=[];

    //in order to give an id to each FavorisComponent
    static counter = 0;

    //http://localhost:8080/ws or /api/ws ?????
    javaUrl = "http://localhost:8080/ws";

    constructor(private httpClient:HttpClient){
        MovieService.searchedMovies = [];
    }

    addMovie(infoMovie:any,isFavorite:boolean){
        var newMovie = {info:infoMovie,isFavorite:isFavorite};
        //TODO: replace the '0' by the real id of the user
        this.httpClient.post(this.javaUrl+"/Movie/add/0",newMovie)
                       .subscribe((movie:any)=>{
                           console.log(movie);
                           console.log(this.movies.length);
                           this.movies.push[movie];
                       });
        MovieService.counter++;
    }

    deleteMovie(id:number){
        this.movies[id] = undefined;
        //TODO: replace the '0' by the real id of the user
        this.httpClient.delete(this.javaUrl+"/Movie/del/0",this.movies[id]);
    }


    getMOviesFromUser(data:string[]){
        this.httpClient.post(this.javaUrl+"/User/connect",data)
                       .subscribe((user:any)=>{
                        MovieService.isConnected = true;
                        this.movies = user.movies;
                       });
    }


    getMoviesFromOMDb(name:string){
        //Because I don't know how to use HttpParams...
        let params = ["?s=","&apikey=f08bf9a0"];
        params[0] += name;

        this.httpClient.get("http://www.omdbapi.com/"+params[0]+params[1])
                       .subscribe((response:any)=>{
                           console.log(response.Search);
                            let success = response.Response == "True";
                            if(success){
                                MovieService.searchedMovies = response.Search;
                            }
                       });
    }

}