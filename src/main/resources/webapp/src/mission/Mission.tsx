import * as React from "react";

export enum MisisonType {
    COOP, COTVT, TVT, GTVT, UNKOWN
}

export interface Session {
    week: number;
    day: string;
}

export interface DateTime {
    description: string;
    year: number;
    month: number;
    day: number;
    hour: number;
    minute: number;
}

export interface Duration {
    hour: 	number;
    minute: number;
    second: number;
}

export interface Weather {
    description: string;
    overcast: number;
    rain: number;
    rainbow: number;
    lightnings: number;
    windStrength: number;
    windGusts: number;
    waves: number;
    humidity: number;
    fogStrength: number;
    fogDecay: number;
    fogBase: number;
}

export interface World {
    name: string,
    displayName: String,
    width: number,
    height: number,
    minZoom: number,
    maxZoom: number
}

export interface Mission {
    id: number;
    created: string;
    type: MisisonType;
    maxPlayers: number;
    name: string,
    world: World;
    session: Session;
    dateTime: DateTime;
    duration: Duration;
    weather: Weather;
    actualPlayers: number;
}

export class MissionRow extends React.Component<Mission, {}> {
    render() {
        return (
            <tr>
                <td className="name"><a href={"mission/" + this.props.id}>{this.props.name}</a></td>
                <td className="type"><span className={"mission-type " + this.props.type}>{this.props.type}</span></td>
                <td className="world">{this.props.world.displayName}</td>
                <td className="players">{this.props.actualPlayers} / {this.props.maxPlayers}</td>
                <td className="session">{this.props.session.week} {this.props.session.day}</td>
                <td className="duration">{this.props.duration.hour}h {this.props.duration.minute}m {this.props.duration.second}s</td>
                <td className="weather">
                    <img className="weather" title={this.props.weather.description} src={`images/${this.props.weather.description}.png`} />
                    <span className="fog" title="Fog">{this.props.weather.fogStrength * 100}%</span>
                </td>
                <td className="time">{this.props.dateTime.description}</td>
                <td className="created">{this.props.created}</td>
            </tr>
        );
    }
}

export interface MissionTableProps {
    missions: Mission[]
}

export class MissionTalbe extends React.Component<MissionTableProps, {}> {
    render() {
        return (
            <table id="mission-table">
                <thead>
                    <tr>
                        <th className="name">Name</th>
                        <th className="type">Type</th>
                        <th className="world">World</th>
                        <th className="players">Players</th>
                        <th className="session">Session</th>
                        <th className="duration">Duration</th>
                        <th className="weather">Weather</th>
                        <th className="time">Mission Time</th>
                        <th className="created">Played Date</th>
                    </tr>
                </thead>
                <tbody>
                    {this.props.missions.map(m => <MissionRow {...m} key={m.id} />)}
                </tbody>
            </table>
        );
    }
}

export interface SearchTerms {
    nameFilter: string;
    typeFilter: string;
    worldFilter: string;
    sessionFilter: string;
}

export interface MissionSearchProps {
    terms: SearchTerms;
    onSearch: (terms: SearchTerms) => void;
}

export class MissionSearch extends React.Component<MissionSearchProps, {}> {
    inputs: {
        nameFilterInput?: HTMLInputElement;
        typeFilterInput?: HTMLInputElement;
        worldFilterInput?: HTMLInputElement;
        sessionFilterInput?: HTMLInputElement;
    } = {};

    handleChange() {
        this.props.onSearch({
            nameFilter: this.inputs.nameFilterInput.value,
            typeFilter: this.inputs.typeFilterInput.value,
            worldFilter: this.inputs.worldFilterInput.value,
            sessionFilter: this.inputs.sessionFilterInput.value
        })
    }

    render() {
        return (
            <div id="search">
                <div className="control">
                    <span className="label">Name:</span>
                    <input ref={inp => this.inputs.nameFilterInput = inp} className="value" value={this.props.terms.nameFilter} onChange={this.handleChange.bind(this)} />
                </div>
                <div className="control">
                    <span className="label">Type:</span>
                    <input ref={inp => this.inputs.typeFilterInput = inp} className="value" value={this.props.terms.typeFilter} onChange={this.handleChange.bind(this)} />
                </div>
                <div className="control">
                    <span className="label">World:</span>
                    <input ref={inp => this.inputs.worldFilterInput = inp} className="value" value={this.props.terms.worldFilter} onChange={this.handleChange.bind(this)} />
                </div>
                <div className="control">
                    <span className="label">Session:</span>
                    <input ref={inp => this.inputs.sessionFilterInput = inp} className="value" value={this.props.terms.sessionFilter} onChange={this.handleChange.bind(this)} />
                </div>
            </div>
        );
    }
}

export interface MissionViewerProps {
    maxVisible: number;
    missions: Mission[]
}

export interface MissionViewerState {
    terms: SearchTerms;
}

export class MissionViewer extends React.Component<MissionViewerProps, MissionViewerState> {
    constructor(props: MissionViewerProps) {
        super(props);
        this.state = {
            terms: {
                nameFilter: '',
                typeFilter: '',
                worldFilter: '',
                sessionFilter: ''
            }
        };
    }

    handleSearch(terms: SearchTerms) {
        this.setState({ terms: terms });
    }

    render() {
        let missions = this.props.missions.filter(m => m.name.search(new RegExp(this.state.terms.nameFilter, 'i')) >= 0)
            .filter(m => m.type.toString().search(new RegExp(this.state.terms.typeFilter, 'i')) >= 0)
            .filter(m => m.world.displayName.search(new RegExp(this.state.terms.worldFilter, 'i')) >= 0)
            .filter(m => `${m.session.week} ${m.session.day}`.search(new RegExp(this.state.terms.sessionFilter, 'i')) >= 0)
            .slice(0, this.props.maxVisible);
        return (
            <div id="mission">
                <MissionSearch terms={this.state.terms} onSearch={this.handleSearch.bind(this)} />
                <MissionTalbe missions={missions} />
                <div id="table-footer">{missions.length} / {this.props.missions.length}</div>
            </div>
        );
    }
}


