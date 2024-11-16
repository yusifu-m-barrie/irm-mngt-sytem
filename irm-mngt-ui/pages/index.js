import { getSession } from "next-auth/react";
import Head from "next/head";
import { useState, useEffect } from "react";
import Sidebar from "../components/Sidebar";
import Chart from "../components/Chart";
import Navbar from "../components/Navbar";
import Login from "../components/Login";

export default function Home({ session }) {
    const [affiliateMembersCount, setAffiliateMembersCount] = useState(0);
    const [announcementsCount, setAnnouncementsCount] = useState(0);
    const [eventsCount, setEventsCount] = useState(0);

    useEffect(() => {
        const fetchCounts = async () => {
            try {
                const [membersRes, announcementsRes, eventsRes] = await Promise.all([
                    fetch("http://localhost:8080/api/v1/affiliateMembers/count"),
                    fetch("http://localhost:8080/api/v1/announcements/count"),
                    fetch("http://localhost:8080/api/v1/events/count")
                ]);

                if (membersRes.ok && announcementsRes.ok && eventsRes.ok) {
                    const membersData = await membersRes.json();
                    const announcementsData = await announcementsRes.json();
                    const eventsData = await eventsRes.json();

                    setAffiliateMembersCount(membersData);
                    setAnnouncementsCount(announcementsData);
                    setEventsCount(eventsData);
                } else {
                    console.error("Failed to fetch one or more counts");
                }
            } catch (error) {
                console.error("Error fetching counts:", error);
            }
        };

        fetchCounts();
    }, []);

    if (!session) return <Login />;

    return (
        <div className="flex min-h-screen bg-gray-100">
            <Head>
                <title>IRM Management Dashboard</title>
            </Head>

            {/* Sidebar */}
            <Sidebar />

            <div className="flex-1 ml-64 p-6"> {/* Added ml-64 for sidebar spacing */}
                {/* Navbar */}
                <Navbar />

                <main className="p-6">
                    <h2 className="text-2xl font-bold mb-4">Dashboard Overview</h2>

                    {/* Charts section */}
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
                        <Chart title="Users" count={affiliateMembersCount} color="bg-blue-500" />
                        <Chart title="Announcements" count={announcementsCount} color="bg-green-500" />
                        <Chart title="Events" count={eventsCount} color="bg-yellow-500" />
                    </div>

                    {/* Add forms */}
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                        {/* Uncomment these lines to display forms */}
                        {/*<AddUser />*/}
                        {/*<AddAnnouncement />*/}
                        {/*<AddEvent />*/}
                    </div>
                </main>
            </div>
        </div>
    );
}

export async function getServerSideProps(context) {
    const session = await getSession(context);
    return {
        props: { session },
    };
}
