import React from "react";
import ReactDom from "react-dom";
import ContentDemo1 from "./content/contentDemo1";
import ContentDemo2 from "./content/contentDemo2";

class Feature extends React.Component {

    handleButtonClick = (e) => {
        const script = this.props.feature.requireScript;
        const contentOptions = {
            contentDemo1: ContentDemo1,
            contentDemo2: ContentDemo2
        };
        const ContentHolder = contentOptions[script];
        ReactDom.render(<ContentHolder options={this.props.feature}/>, document.getElementById(this.props.contentId));
    }

    render() {
        if (this.props.feature.type == 'BUTTON')
            return (
                <button width="100px" height="20px" onClick={this.handleButtonClick}>
                    {this.props.feature.caption}
                </button>
            )
    }
}

module.exports = Feature;