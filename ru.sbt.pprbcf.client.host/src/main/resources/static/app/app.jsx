import React from "react";
import ReactDom from "react-dom";
import IClient from "./iClient";
import ReactGridLayout from "react-grid-layout";
import restClient from "./restClient";

class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            login: document.getElementById('login').textContent
        }
    }

    componentDidMount() {
        restClient({
            method: 'GET', path: '/clients/' + this.state.login
        }).then(
            response => {
                let clients = response.entity;

                const layout = [
                    {i: '1', x: 0, y: 0, w: 12, h: 1, static: true},
                    {i: 'Demo client', x: 0, y: 1, w: 3, h: 2, minW: 2, maxW: 4},
                    {i: 'Test2', x: 0, y: 2, w: 3, h: 2, minW: 2, maxW: 4}
                ];

                let divs = [];
                if (clients != null && clients.length > 0)
                    divs = clients.map(client => <div key={client.appName}><IClient baseUrl={client.url}
                                                                             login={this.state.login}/></div>);
                divs.unshift(<div key={'1'}><h1>Hello from host, {this.state.login}!</h1></div>);

                this.setState({layout: layout, divs: divs});
            }
        );
    }

    render() {
        return (
            <ReactGridLayout className="layout" layout={this.state.layout} cols={12} width={1200}>
                {this.state.divs}
            </ReactGridLayout>
        )
    }
}

ReactDom.render(<App />, document.getElementById('react'));