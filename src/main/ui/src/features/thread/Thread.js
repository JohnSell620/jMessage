import React from 'react';
import './Thread.scss';
import ClientService from '../../ClientService';

class Thread extends React.Component {
  render() {
    return (
      <div className="thread-inlist">
        <div className="thread-title" onClick={() => this.getConversationAndUsers(this.props.title)}>
          {this.props.title}
        </div>
      </div>
    );
  }
  getConversationAndUsers(thread) {
      ClientService.getConversationMessages(thread);
      ClientService.getConversationUsers(thread);
  }
}

export default Thread;
