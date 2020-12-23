import React, { Component } from 'react';
import UserList from './features/users/UserList'
import ClientService from './ClientService'
import logo from './logo.svg';
import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.clientService = new ClientService();
    this.state = {
      threads: [],
      conversation: [],
      users: []
    };
  }

  componentDidMount() {
    this.clientService.getUserProfiles().then(userProfiles => {
      this.setState({users: userProfiles})
    });
  }

  render() {
    return (
      <UserList users={this.state.users}/>
    )
  }
}

export default App;
