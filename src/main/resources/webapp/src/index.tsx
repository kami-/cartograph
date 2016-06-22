import * as React from "react";
import * as ReactDOM from "react-dom";

import { MissionViewer, Mission } from "./mission/Mission";

let request = new XMLHttpRequest();
request.onreadystatechange = () => {
    if (request.readyState === 4) {
        if (request.status === 200) {
            renderPage(JSON.parse(request.responseText));
        } else {
            console.log("error", request.responseText)
        }
    }
}
request.open('GET', 'api/mission');
request.send(null)

function renderPage(missions: Mission[]) {
    console.log("renderPage", missions);
    ReactDOM.render(
        <MissionViewer maxVisible={30} missions={missions} />,
        document.getElementById("container")
    );
}