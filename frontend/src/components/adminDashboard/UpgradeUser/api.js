export const UpgradeUserAPI = async (values, actions) => {
  console.log(values);
  const url = `http://localhost:8080/admin/upgrade?email=${values.email}`;
  try {
    const response = await fetch(url, {
      method: "PUT",
    });
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
    actions.setStatus("success");
  } catch (error) {
    actions.setStatus("fail");
    console.error(error.message);
  }
};
