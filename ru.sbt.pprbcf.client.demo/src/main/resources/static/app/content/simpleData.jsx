import React from "react";
import restClient from "./../restClient";

class SimpleDataHolder extends React.Component {

    constructor(props) {
        super(props);

        this.state = {data: [],
            servicePath: this.props.options.links.find(link => link.rel == this.props.options.caption).href};
    }

    componentDidMount() {
        restClient({
            method: 'GET', path: this.state.servicePath
        }).then(
            response => {
                this.setState({data: response.entity});
            }
        );
    }

    render() {
        return (
            <SimpleDataList data={this.state.data}/>
        )
    }
}

class SimpleDataList extends React.Component {
    render() {
        var data = this.props.data.map(
            item => <SimpleDataItem key={item.id} item={item}/>
        )

        return (
            <table>
                <tbody>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>DESC</th>
                </tr>
                {data}
                </tbody>
            </table>
        )
    }
}

class SimpleDataItem extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.item.id}</td>
                <td>{this.props.item.name}</td>
                <td>{this.props.item.desc}</td>
            </tr>
        )
    }
}

module.exports = SimpleDataHolder;