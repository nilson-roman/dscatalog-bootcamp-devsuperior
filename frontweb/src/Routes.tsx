import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';
import Home from 'pages/Home';
import Navbar from 'components/Navbar';
import Catalog from 'pages/Catalog';
import ProductDetails from 'pages/ProductDetails';
import Admin from 'pages/Admin';

const Routes = () => {
  return (
    <BrowserRouter>
      <Navbar />
      <Switch>
          <Route path="/" exact>
              <Home />
          </Route>
          <Route path="/products" exact>
              <Catalog />
          </Route>
          <Route path="/products/:productId">
              <ProductDetails />
          </Route>
          <Redirect from="/admin" to="/admin/products" exact />
          <Route path="/admin">
              <Admin />
          </Route>
      </Switch>
    </BrowserRouter>
  );
};

export default Routes;
