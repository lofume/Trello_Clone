import React, { useEffect, useState } from 'react';
import ViewWorkspaces from '../components/ViewWorkspaces';

function Workspaces() {
    const [workspacesData, setWorkspacesData] = useState([]);
    const userId = localStorage.getItem('userId');

    function getAllWorkspaces() {
        fetch(`http://localhost:9001/user/getWorkspaces/${userId}`)
            .then(response => response.json())
            .then(workspaces => {
                setWorkspacesData(workspaces);
            });
    };

    useEffect(function () {
        getAllWorkspaces();
    }, []);

    return (
        <section>
            <ViewWorkspaces workspaces={workspacesData} />
        </section>
    );
};

export default Workspaces;