import React from "react";

export default class SpecialSwitches extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="form-group col-md-6">
                <div className="custom-control custom-switch mt-1">
                    <input type="checkbox" className="custom-control-input" id="requireDigitSwitch"
                            name="requireDigit" value="" defaultChecked={this.props.requireDigit} onChange={this.props.onChange}/>
                    <label className="custom-control-label" htmlFor="requireDigitSwitch" title="Click to toggle">
                        Require numeric character
                    </label>
                </div>
                <div className="custom-control custom-switch">
                    <input type="checkbox" className="custom-control-input" id="requireSpecialSwitch"
                           name="requireSpecial" value="" defaultChecked={this.props.requireSpecial} onChange={this.props.onChange}/>
                    <label className="custom-control-label" htmlFor="requireSpecialSwitch" title="Click to toggle">
                        Require special character
                    </label>
                </div>
            </div>
        );
    }
}
