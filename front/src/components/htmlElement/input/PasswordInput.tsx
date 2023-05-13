import { ChangeEvent } from "react";
import { FocusEvent } from "react";
import styles from "@/src/components/htmlElement/input/input.module.css";

type PasswordInputProps = {
  label: string;
  tag: string;
  value: string;
  onChange?: (e: ChangeEvent<HTMLInputElement>) => void;
  onBlur?: (e: FocusEvent<HTMLInputElement>) => void;
  required?: boolean;
  maxLength?: number;
  errorMessage?: string;
};

export const PasswordInput: React.FC<PasswordInputProps> = ({
  label,
  tag,
  value,
  onChange,
  onBlur,
  required,
  maxLength,
  errorMessage,
}) => {
  return (
    <div className={styles["input-container"]}>
      <label htmlFor={tag}>{label}: </label>
      <input
        id={tag}
        type="password"
        name={tag}
        value={value}
        placeholder={tag}
        onChange={onChange}
        onBlur={onBlur}
        {...(required ? { required } : {})}
        className={styles.input}
        maxLength={maxLength}
      />
      {errorMessage && <p className={styles.error}>{errorMessage}</p>}
    </div>
  );
};
