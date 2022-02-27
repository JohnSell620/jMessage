import React from 'react';
import './Thread.scss';
import Button from '../controls/Button';

class Thread extends React.Component {
  render() {
    return (
      <div className="thread-inlist">
        <div className="thread-title">
          <Button label={this.props.title} variant="basic" getConversationMessages={() => this.props.onGetConversationMessages()} getConversationUsers={() => this.props.onGetConversationUsers()}/>
        </div>
      </div>
    );
  }
}

export default Thread;
