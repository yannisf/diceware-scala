import React from "react";

export default class NumberOfPasswordsSelector extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="form-group col-md-4">
                <label htmlFor="numberOfPasswords">Number of passwords</label>
                <input id="numberOfPasswords"
                       type="number" min="1" max="5"
                       className="form-control"
                       name="numberOfPasswords"
                       value={this.props.numberOPasswords}
                       onChange={this.props.onChange}/>
            </div>
        );
    }
}
