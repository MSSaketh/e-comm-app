import {CommonModule} from '@angular/common';
import {HttpModule} from '@angular/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core'; 
import {UserLoginComponent} from './user.login.component';
import {UserLoginService} from './user.login.service';
import { ProductCatalogComponent } from '../productCatalog/product.catalog.component';
const cartRoutes: Routes = [
    { path: 'catalog', component:  ProductCatalogComponent },
  ]

 @NgModule({
    declarations: [
      UserLoginComponent
    ],
    imports: [
        CommonModule,
        HttpModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule.forChild (cartRoutes)
    ],
    exports: [],
   providers: [
     UserLoginService
   ]
 })
 export class UserLoginModule {}
