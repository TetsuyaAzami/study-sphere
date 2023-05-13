import styles from "@/src/components/input/input.module.css";
import { FocusEvent } from "react";
import { ChangeEvent } from "react";

type TextInputProps = {
  label: string;
  tag: string;
  value: string;
  onChange?: (e: ChangeEvent<HTMLInputElement>) => void;
  onBlur?: (e: FocusEvent<HTMLInputElement>) => void;
  required?: boolean;
  maxLength?: number;
  errorMessage?: string;
};

export const TextInput: React.FC<TextInputProps> = ({
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
        type="text"
        name={tag}
        value={value}
        placeholder={tag}
        onChange={onChange}
        onBlur={onBlur}
        {...(required ? { required: true } : {})}
        className={styles.input}
        maxLength={maxLength}
      />
      {errorMessage && <p className={styles.error}>{errorMessage}</p>}
    </div>
  );
};
