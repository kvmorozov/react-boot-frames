import React from "react";
import ReactDom from "react-dom";
import restClient from "./restClient";
import Feature from "./feature.jsx";

class App extends React.Component {

    componentDidMount() {
        restClient({
            method: 'GET', path: '/init'
        }).then(
            response => {
                let data = response.entity;

                this.setState({login: data.currentUser, features: data.features});
            }
        );
    }

    render() {
        if (this.state == null || this.state.login == null || this.state.features == null)
            return (
                <div>
                    <h1>Hello from client, anonym!</h1>
                </div>
            );

        let user = this.state.login;
        let features = this.state.features.map(
            feature => <Feature key={feature.caption} feature={feature} contentId="content"/>
        );

        return (
            <div id="container" height="80%">
                <h1>Hello from client, {user}!</h1>

                {features}
                <div id="content"/>
            </div>);
    }
}

ReactDom.render(<App />, document.getElementById('react'));