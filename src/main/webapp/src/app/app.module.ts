import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { FavorisComponent } from './favoris/favoris.component';
import { FormsModule } from '@angular/forms';
import { MovieService } from './services/movie.service';
import { MovieComponent } from './movie/movie.component';
import { RouterModule, Routes } from '@angular/router';
import { MovieViewComponent } from './movie-view/movie-view.component';
import { AuthComponent } from './auth/auth.component';
import { ModalModule } from 'ngx-bootstrap/modal';

const appRoute: Routes = [
  {path:"movies",component:MovieViewComponent},
  {path:"",component:AuthComponent},
  {path:"auth",component:AuthComponent},
  {path:"favoris",component:FavorisComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    FavorisComponent,
    MovieComponent,
    MovieViewComponent,
    AuthComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoute),
    ModalModule.forRoot()
  ],
  providers: [
    MovieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
