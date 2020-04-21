import React from "react";

export default class DicewareResponse extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="card bg-light mb-3">
                <div className="card-header">The password is</div>
                <div className="card-body">
                    <pre>{this.props.password}</pre>
                </div>
            </div>
        );
    }
}
