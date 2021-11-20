import React, { Component } from 'react';

import Conversation from './features/conversation/Conversation';
import ThreadList from './features/thread/ThreadList';
import UserList from './features/users/UserList';
import ClientService from './ClientService';

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
    this.clientService.getThreads().then(threads => {
      this.setState({threads: threads})
    });
  }

  render() {
    return (
      <div className="page">
        <ThreadList threads={this.state.threads}/>
        <Conversation messages={this.state.conversation}/>
        <UserList users={this.state.users}/>
      </div>
    );
  }
}

export default App;
