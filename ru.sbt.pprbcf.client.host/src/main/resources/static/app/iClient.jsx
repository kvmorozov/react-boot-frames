import React from "react";

class IClient extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            url: this.props.baseUrl + '?name=' + this.props.login
        }
    }

    render() {
        return (<iframe src={this.state.url} width="500" height="250"
                        frameBorder="yes" style={{border: "2px solid black"}} seamless
                        title={this.props.login}/>
        )
    }
}

module.exports = IClient;