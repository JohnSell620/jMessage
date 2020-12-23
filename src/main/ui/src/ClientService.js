const DATA_URL = "http://localhost:8080/jMessage/webapi/"

class ClientService {
  constructor() {
    this.config = DATA_URL;
  }

  async getUserProfiles() {
    return fetch(this.config + "profiles")
      .then(response => {
        if (!response.ok) {
          this.handleResponseError(response);
        }
        return response.json();
      })
      .then(json => {
        console.log("Retrieved items:");
        console.log(json);
        const items = [];
        const itemArray = json._embedded.collectionItems;
        for(var i = 0; i < itemArray.length; i++) {
          items.push(itemArray[i]["profileName"]);
        }
        return items;
      })
      .catch(error => {
        console.log(error);
      });
  }
}

export default ClientService;
