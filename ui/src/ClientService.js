class ClientService {
  async getUserProfiles() {
    return getData("/webapi/profiles/");
  }
  async getConversationMessages(thread) {
    return getData("/webapi/messages/" + thread + "/");
  }
  async getConversationUsers(thread) {
    return getData("/webapi/threads/" + thread + "/users");
  }
  async getThreads() {
    return getData("/webapi/threads/");
  }
}

function getData(url) {
  return fetch(url, {
    method: 'GET',
    mode: 'cors',
    headers: {
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'GET',
      'Access-Control-Allow-Headers': '*',
      'Content-Type': 'application/json'
    },
    redirect: 'follow',
    referrePolicy: 'no-referrer'
  })
  .then(response => {
    if (!response.ok) {
      console.log(response.statusText);
    }
    return response.json();
  })
  .then(json => {
    // console.log(json);
    return json;
  })
  .catch(error => {
    console.log(error);
  });
}

export default ClientService;
