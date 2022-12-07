import { NavLink } from "react-router-dom";
import { toTitleCase } from "../../helpers/utility";

export default function Grid(props) {
    let viewRoute;
    const list = props.list;
    const columns = list && list[0] ? Object.keys(list[0]) : [];
    const hasActions = !!(props.actions);
    if (hasActions) {
        viewRoute = props.action.viewRoute;
    }
    // const deleteMethod = props.deleteMethod;
    return (
        <div>
            {
                columns.length > 0 ?
                    <div>
                        <table className="table table-bordered">
                            <thead>
                                <tr key="list-row-head">
                                    {
                                        columns.map((k) => {
                                            return (<th key={"list-header-cell" + k} scope="col">{toTitleCase(k)}</th>);
                                        })
                                    }
                                    {hasActions ? <th>Action</th> : null}
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    list.map(p => {
                                        return (<tr key={"list-row-head" + p.id}>
                                            {
                                                Object.keys(p).map((k) => {
                                                    return (<td key={"list-colum" + k + p[k]}>
                                                        {p[k]}
                                                    </td>)
                                                })
                                            }
                                            {
                                                hasActions ? <td>
                                                    {
                                                        viewRoute ? <NavLink to={viewRoute + p.id}>View</NavLink> : null
                                                    }

                                                </td> : null
                                            }

                                        </tr>);
                                    })
                                }
                            </tbody>
                        </table>
                    </div> :
                    <div>
                        <h1>List is empty</h1>
                    </div>
            }
        </div>
    );
}