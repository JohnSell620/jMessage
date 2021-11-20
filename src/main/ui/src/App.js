import React, { Component } from 'react';
// import { Switch, Route } from 'react-router-dom';

import Conversation from './features/conversation/Conversation';
import ThreadList from './features/thread/ThreadList';
import UserList from './features/users/UserList';
import ClientService from './ClientService';
// import LoginContainer from './features/controls/LoginContainer';

class App extends Component {
  constructor(props) {
    super(props);
    this.clientService = new ClientService();
    this.state = {
      threads: [],
      conversation: [],
      users: []
    };
    this.getConversationMessages = this.getConversationMessages.bind(this);
    this.getConversationUsers = this.getConversationUsers.bind(this);
  }

  componentDidMount() {
    this.clientService.getThreads().then(threads => {
      this.setState({threads: threads})
    });
  }

  getConversationMessages(thread) {
    this.clientService.getConversationMessages(thread).then(conversation => {
      this.setState({conversation: conversation})
    });
  }

  getConversationUsers(thread) {
    this.clientService.getConversationUsers(thread).then(users => {
      this.setState({users: users})
    });
  }

  render() {
    // return (
    //   <div className="app-routes">
    //     <Switch>
    //       <Route path="/login" component={LoginContainer} />
    //       <Route path="/" component={Cms} />
    //     </Switch>
    //   </div>
    // );
    return (
      <div className="page">
        <ThreadList threads={this.state.threads} onGetConversationMessages={this.getConversationMessages} onGetConversationUsers={this.getConversationUsers}/>
        <Conversation messages={this.state.conversation}/>
        <UserList users={this.state.users}/>
      </div>
    );
  }
}

export default App;
