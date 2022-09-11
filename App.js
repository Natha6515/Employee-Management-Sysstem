import './App.css';
import React from 'react';
import { Route , Redirect, Switch } from 'react-router-dom';
import Home from './Components/Home';
import AddEmployee from './Components/AddEmployee';
import AllData from './Components/AllData';
import Search from './Components/Search';
import UpdateEmployee from './Components/UpdateEmployee';


function App() {
  return (
    <>
        
        <Home/>
        <Switch>
            <Route exect path='/home' component={Home}></Route>
            <Route exect path='/addEmployee' component={AddEmployee}></Route>
            <Route exect path='/showEmployees' component={AllData}></Route>
            <Route exect path='/searchEmployee/:data' component={Search}></Route>
            <Route exect path='/editEmployee/:id' component={UpdateEmployee}></Route>
            <Redirect to="/"></Redirect>

        </Switch>
    
    </>
  );
}

export default App;
