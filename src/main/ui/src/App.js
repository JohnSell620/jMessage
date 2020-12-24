import React, { Component } from 'react';

import Conversation from './features/conversation/Conversation';
import UserList from './features/users/UserList';
import ClientService from './ClientService';
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
    this.clientService.getConversationMessages().then(messages => {
      this.setState({conversation: messages})
    });
  }

  render() {
    return (
      <div className="page">
        <Conversation messages={this.state.conversation}/>
        <UserList users={this.state.users}/>
      </div>
    );
  }
}

export default App;
