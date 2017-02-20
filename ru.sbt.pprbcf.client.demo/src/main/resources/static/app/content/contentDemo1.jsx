import React from "react";
import SimpleDataHolder from "./simpleData";

class ContentDemo1 extends React.Component {

    render() {
        return (
            <div>
                <h2>Hello from ContentDemo1!</h2>
                <SimpleDataHolder options={this.props.options}/>
            </div>
        )
    }
}

module.exports = ContentDemo1;