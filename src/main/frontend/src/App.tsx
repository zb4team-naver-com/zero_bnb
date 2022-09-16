import React, {useEffect} from 'react';
import Router from './router/Router'
import axios from 'axios';

function App() {

  useEffect(() => {
    axios.get('/test')
    .then(response => console.log(response))
    .catch(error => console.log(error))
  }, []);

  return <Router/>;
}

export default App;