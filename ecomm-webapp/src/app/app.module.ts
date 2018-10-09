
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {ProductCatalogModule} from './productCatalog/product.catalog.module'
import {AppComponent} from './app.component';
import {ProductCatalogComponent} from './productCatalog/product.catalog.component';
import {RouterModule, Routes} from '@angular/router';
import {CartModule} from "./cart/cart.module";
import { UserLoginModule } from './userLogin/user.login.module';
import { UserLoginComponent } from './userLogin/user.login.component';
import { HttpClientModule } from '@angular/common/http';
const appRoutes: Routes = [
  { path: 'login', component:  UserLoginComponent }
]

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    ProductCatalogModule,
    CartModule,
    UserLoginModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
